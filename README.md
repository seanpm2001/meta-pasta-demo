# meta-pasta-demo #

Yocto layer for the pasta demo

## How to Use ##

- Install the base Torizon BSP and apply the patches from the _patches_ directory.
- Copy the _local.conf_ file from _conf/local.conf.example_ to the build directory.
- Add the greengrass core device credentials to _recipes-aws/aws-iot-greengrass-core-software/files/core-device-secret.tar.gz_.
- (optional) add the Aktualizr OTA credentials to _recipes-sota/aktualizr/files/credentials.zip_ and uncomment related lines at:
    - _conf/local.conf.example_
    - _recipes-images/images/pasta-demo.bb_
- Build the _pasta-demo_ image.