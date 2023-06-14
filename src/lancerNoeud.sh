#!/bin/bash

ssh 100.64.80."$0" "scp -r ../src \$(whoami)@100.64.80.$0:/var/tmp && cd /var/tmp/src/Serveur && java LancerNoeud "$1" 1099 &"