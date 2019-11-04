# meta-pasta-demo #

Yocto layer for the pasta demo

## How to Use ##

- Prepare the build environment as if you would build TorizonCore.
- Clone this repo to _layers_ and add it to _bblayers.conf_.
- Install the base Torizon BSP and apply the patches from the _patches_ directory.
- Copy the _local.conf_ file from _conf/local.conf.example_ to the build directory.
    - Make sure to accept the FSL_EULA, since the accept variable is commented in the sample file. the EULA is at _layers/meta-freescale/EULA_.
- (optional) Add the greengrass core device credentials to _recipes-aws/aws-iot-greengrass-core-software/files/core-device-secret.tar.gz_
and add `aws-iot-greengrass-core-software-device-credentials` to _recipes-images/images/pasta-demo.bb_.
    - If you skip this step, you can add the credentials after the image is installed on the target.
- (optional) add the Aktualizr OTA credentials to _recipes-sota/aktualizr/files/credentials.zip_ and uncomment related lines at:
    - _conf/local.conf.example_
    - _recipes-images/images/pasta-demo.bb_
- Build the _pasta-demo_ image.

### Configure Script ###

A helper script is provided for applying the patches and setting up local.conf and bblayers.conf. It must be run from the _meta-pasta-demo_ root directory:

```
cd <path to meta-pasta-demo inside the layers directory>
./config.sh
```