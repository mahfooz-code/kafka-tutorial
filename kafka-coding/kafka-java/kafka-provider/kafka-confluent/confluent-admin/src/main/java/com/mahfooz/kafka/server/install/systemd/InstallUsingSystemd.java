/*

1)  Install the Confluent public key. This key is used to sign the packages in the APT repository.

    wget -qO - https://packages.confluent.io/deb/5.4/archive.key | sudo apt-key add -

2)  Add the repository to your /etc/apt/sources.list by running this command:

    sudo add-apt-repository "deb [arch=amd64] https://packages.confluent.io/deb/5.4 stable main"

3)  Update apt-get and install the entire Confluent Platform platform.

    Confluent Platform:
        sudo apt-get update && sudo apt-get install confluent-platform-2.12

    Confluent Platform with RBAC:
        sudo apt-get update && sudo apt-get install confluent-platform-2.12 && \
        sudo apt-get install confluent-server

    Confluent Platform using only Confluent Community components:
        sudo apt-get update && sudo apt-get install confluent-community-2.12

 */
package com.mahfooz.kafka.server.install.systemd;

public class InstallUsingSystemd {
}
