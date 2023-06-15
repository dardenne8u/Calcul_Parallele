#!/bin/bash
echo $2
utilisateur=$(whoami)
scp -r ../../Calcul_Parallele $utilisateur@100.64.80.$1:/var/tmp
ssh 100.64.80.$1 "cd /var/tmp/Calcul_Parallele/src && java Serveur.LancerNoeud "$2" 1099 &"