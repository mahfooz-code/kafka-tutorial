#!/bin/bash

set -o nounset \
    -o errexit \
    -o verbose \
    -o xtrace

# Generate CA key
openssl req -new -x509 \
  -keyout snakeoil-ca-1.key \
  -out snakeoil-ca-1.crt -days 365 \
  -subj '/CN=in.test.mahfooz.io/OU=Dev/O=MahfoozSoft/L=Chennai/ST=TN/C=IN' \
  -passin pass:confluent -passout pass:confluent

# Kafkacat
openssl genrsa -des3 -passout "pass:confluent" -out kafkacat.client.key 1024
openssl req -passin "pass:confluent" -passout "pass:confluent" \
  -key kafkacat.client.key -new -out kafkacat.client.req \
  -subj '/CN=kafkacat.test.mahfooz.io/OU=TEST/O=CONFLUENT/L=PaloAlto/S=Ca/C=US'

openssl x509 -req -CA snakeoil-ca-1.crt \
  -CAkey snakeoil-ca-1.key -in kafkacat.client.req -out kafkacat-ca1-signed.pem -days 9999 \
  -CAcreateserial -passin "pass:confluent"


for i in broker-1 broker-2 broker-3 producer consumer
do
	echo $i
	mkdir ./$i
	# Create keystores
	keytool -genkey -noprompt \
				 -alias $i \
				 -dname "CN=kafka-$i, OU=Dev, O=MahfoozSoft, L=Chennai, ST=TN, C=IN" \
				 -keystore ./$i/kafka.$i.keystore.jks \
				 -keyalg RSA \
				 -storepass confluent \
				 -keypass confluent

	# Create CSR, sign the key and import back into keystore
	keytool -keystore ./$i/kafka.$i.keystore.jks -alias $i -certreq -file $i.csr -storepass confluent -keypass confluent

	openssl x509 -req -CA snakeoil-ca-1.crt -CAkey snakeoil-ca-1.key -in $i.csr -out $i-ca1-signed.crt -days 9999 -CAcreateserial -passin pass:confluent

	keytool -keystore ./$i/kafka.$i.keystore.jks -alias CARoot -import -file snakeoil-ca-1.crt -storepass confluent -keypass confluent

	keytool -keystore ./$i/kafka.$i.keystore.jks -alias $i -import -file $i-ca1-signed.crt -storepass confluent -keypass confluent

	# Create truststore and import the CA cert.
	keytool -keystore ./$i/kafka.$i.truststore.jks -alias CARoot -import -file snakeoil-ca-1.crt -storepass confluent -keypass confluent

  echo "confluent" > ./$i/${i}_sslkey_creds
  echo "confluent" > ./$i/${i}_keystore_creds
  echo "confluent" > ./$i/${i}_truststore_creds
done
