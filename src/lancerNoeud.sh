#!/bin/bash
utilisateur=$(whoami)
scp -r ../../Calcul_Parallele $utilisateur@100.64.80.$0:/var/tmp
ssh 100.64.80.$0 "cd /var/tmp/Calcul_Parallele/src/Serveur && java LancerNoeud "$1" 1099 &"