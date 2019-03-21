# System wide environment and startup programs, for login setup
# Functions and aliases go in /etc/bashrc

####################### My additional settings
USER_NAME=`whoami`

#proxy settings
export HTTP_PROXY=http://$USER_NAME@proxy.my.local:8080
export HTTPS_PROXY=http://$USER_NAME@proxy.my.local:8080
export NO_PROXY=169.254.169.254,localhost,127.0.0.0
