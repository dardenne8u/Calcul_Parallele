#!/bin/bash
# Please execute this file at the src folder
if [ $# -ne 3 ];
then 
    echo "Please specify three arguments (host IP, first invite SSH IP, last invite SSH IP, in the form of a int that will complete 100.64.80.$)"
    exit 1
fi
utilisateur=$(whoami)
echo "Compiling files..."
javac Serveur/*.java
javac raytracer/*.java
javac LancerRaytracer.java
echo "Creating the server..."
java Serveur.Serveur &
for ((counter=$2; counter <= $3; counter++))
do
    echo "Creating a process at 100.64.80.$counter"
    scp -r ../../Calcul_Parallele $utilisateur@100.64.80.$counter:/var/tmp
    ssh 100.64.80.$counter "cd /var/tmp/Calcul_Parallele/src && java Serveur.LancerNoeud &" 
done
echo "Done, you can launch LancerRaytracer"
exit 0