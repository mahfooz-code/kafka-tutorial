#Generate SSL key and certificate for each Kafka broker
keytool -keystore kafka.server.keystore.jks -alias localhost -validity {validity} -genkey

#Ensure that common name (CN) matches exactly with the fully qualified domain name (FQDN) of the server.
#The client compares the CN with the DNS domain name to ensure that it is indeed connecting to the desired server, not a malicious one.

#Creating your own CA
openssl req -new -x509 -keyout ca-key -out ca-cert -days 365

#The next step is to add the generated CA to the clients’ truststore so that the clients can trust this CA:
keytool -keystore kafka.server.truststore.jks -alias CARoot -import -file ca-cert

keytool -keystore kafka.client.truststore.jks -alias CARoot -import -file ca-cert

#Signing the certificate
keytool -keystore kafka.server.keystore.jks -alias localhost -certreq -file cert-file
openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days {validity} -CAcreateserial -passin pass:{ca-password}

#Finally, you need to import both the certificate of the CA and the signed certificate into the keystore:

keytool -keystore kafka.server.keystore.jks -alias CARoot -import -file ca-cert
keytool -keystore kafka.server.keystore.jks -alias localhost -import -file cert-signed
