[root@bastion ~]# ping proxy.my.local                     │  perl-Socket.x86_64 0:2.010-4.el7
PING proxy.my.local (10.0.0.90) 56(84) bytes of data.     │  perl-Storable.x86_64 0:2.45-3.el7
                                                          │  perl-Text-ParseWords.noarch 0:3.29-4.el7
--- proxy.my.local ping statistics ---                    │  perl-Time-HiRes.x86_64 4:1.9725-3.el7
16 packets transmitted, 0 received, 100% packet loss, time│  perl-Time-Local.noarch 0:1.2300-2.el7
 14999ms
-------------------------------------------------

▼ 下記をインバウンドとアウトバウンドとに追加することで解決
-------------------------------------------------
- IpProtocol: icmp
  FromPort: 8
  ToPort: -1
  CidrIp: !ImportValue Vpc-MyVpcCidrBlock
  Description: ping from MyVPC
-------------------------------------------------
