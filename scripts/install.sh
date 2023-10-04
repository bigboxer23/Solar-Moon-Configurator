#!/usr/bin/env bash
host=${host}
user=${user}
identity_file=${identity_file}

scp -i ${identity_file} -o StrictHostKeyChecking=no -r solar-moon-backend.service $user@$host:~/
ssh -i ${identity_file} -t $user@$host -o StrictHostKeyChecking=no "sudo mv ~/solar-moon-backend.service /lib/systemd/system"
ssh -i ${identity_file} -t $user@$host -o StrictHostKeyChecking=no "sudo systemctl daemon-reload"
ssh -i ${identity_file} -t $user@$host -o StrictHostKeyChecking=no "sudo systemctl enable solar-moon-backend.service"
ssh -i ${identity_file} -t $user@$host -o StrictHostKeyChecking=no "sudo systemctl start solar-moon-backend.service"