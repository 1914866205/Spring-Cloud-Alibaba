#! /bin/bash
# author:nitaotao
# Reload nginx and deploy vue

echo "1: Uninstall nginx"

echo "1.1 Find all file or mkdir about nginx,and delete it"
sudo find / -name nginx*
sudo rm -rf file /usr/lib/systemd/system/nginx-debug.service
sudo rm -rf file /usr/lib/systemd/system/nginx.service
sudo rm -rf file /usr/lib64/nginx
sudo rm -rf file /usr/sbin/nginx-debug
sudo rm -rf file /usr/share/doc/nginx-1.18.0
sudo rm -rf file /usr/share/man/man8/nginx.8.gz
sudo rm -rf file /usr/share/nginx
sudo rm -rf file /usr/local/nginx
sudo rm -rf file /usr/libexec/initscripts/legacy-actions/nginx
sudo rm -rf file /etc/logrotate.d/nginx
sudo rm -rf file /etc/sysconfig/nginx-debug
sudo rm -rf file /etc/sysconfig/nginx
sudo rm -rf file /var/lib/yum/repos/x86_64/7/nginx
sudo rm -rf file /var/cache/nginx

echo "checking"
sudo find / -name nginx*

echo "2. Reload nginx"
echo "2.1 Installation dependency package"
yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
echo "2.2 Download and unzip the installation package"
cd /usr/local
mkdir nginx
cd nginx
wget http://nginx.org/download/nginx-1.13.7.tar.gz
tar -xvf nginx-1.13.7.tar.gz

echo "2.3 Install nginx"
cd /usr/local/nginx
./configure
make
make install

echo "2.4 Configuration and deploy vue"

rm -rf /usr/local/nginx/nginx-1.13.7/html/*
mv -f /dist/nginx.conf  /usr/local/nginx/nginx-1.13.7/conf
mv -f /dist/index.html  /usr/local/nginx/html
mv -f /dist/assets  /usr/local/nginx/html

echo "2.5 start Nginx"
/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf
/usr/local/nginx/sbin/nginx -s reload

echo "2.6 checking"
ps -ef | grep nginx

echo "If it fails, please contact me,QQ:1914866205"
