# docker image inspect richxsl/rhel6.5:latest


```sh
yasuakishibata@YS-Mac in_my_head $ docker image inspect richxsl/rhel6.5:latest
[
    {
        "Id": "sha256:ea4a156ca854e8abc847f63b09834d991f923244f5004a4501ebba780a3ec723",
        "RepoTags": [
            "richxsl/rhel6.5:latest"
        ],
        "RepoDigests": [
            "richxsl/rhel6.5@sha256:7528fece03ddae4e43f41999e5965bebdd7602ff8d1d24b3b6102e4cd2c3108e"
        ],
        "Parent": "",
        "Comment": "Imported from -",
        "Created": "2014-12-09T11:28:02.228408814Z",
        "Container": "",
        "ContainerConfig": {
            "Hostname": "",
            "Domainname": "",
            "User": "",
            "AttachStdin": false,
            "AttachStdout": false,
            "AttachStderr": false,
            "Tty": false,
            "OpenStdin": false,
            "StdinOnce": false,
            "Env": null,
            "Cmd": null,
            "Image": "",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": null
        },
        "DockerVersion": "1.2.0",
        "Author": "",
        "Config": null,
        "Architecture": "amd64",
        "Os": "linux",
        "Size": 229456995,
        "VirtualSize": 229456995,
        "GraphDriver": {
            "Data": {
                "MergedDir": "/var/lib/docker/overlay2/f1f856955e6d8638eeef3b27af788a21135eebc28a35683a07558559c1961f8b/merged",
                "UpperDir": "/var/lib/docker/overlay2/f1f856955e6d8638eeef3b27af788a21135eebc28a35683a07558559c1961f8b/diff",
                "WorkDir": "/var/lib/docker/overlay2/f1f856955e6d8638eeef3b27af788a21135eebc28a35683a07558559c1961f8b/work"
            },
            "Name": "overlay2"
        },
        "RootFS": {
            "Type": "layers",
            "Layers": [
                "sha256:4922eb4e656a2c8e98e686e19ca43ee432566c11cdf8c354da346b7ee207c7d8"
            ]
        },
        "Metadata": {
            "LastTagTime": "0001-01-01T00:00:00Z"
        }
    }
]
```
