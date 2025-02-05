# Step 1: Download and Start Confluent Platform

# Set the environment variable for the Confluent Platform directory.

  export CONFLUENT_HOME=<path-to-confluent>

# Add the Confluent Platform bin directory to your PATH.

  export PATH=$PATH:$CONFLUENT_HOME/bin

# Install the Confluent CLI, confluent, using the following script.
# The <path-to-cli> parameter must be in your PATH (e.g. /usr/local/bin)

  curl -L --http1.1 https://cnfl.io/cli | sh -s -- -b /usr/local/bin

#Install the Kafka Connect Datagen source connector using the Confluent Hub client

  $CONFLUENT_HOME/bin/confluent-hub install \
    --no-prompt confluentinc/kafka-connect-datagen:latest

#Start Confluent Platform using the Confluent CLI confluent local start command.

  confluent local start

#Stop Confluent Platform

  confluent local stop

#Destroy the data in the Confluent Platform instance with the confluent local destroy command.

  confluent local destroy
