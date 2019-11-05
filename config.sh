#!/bin/bash

# Some very basic sanity check
if [ -f "README.md" ] && [ -d "recipes-aws" ] && [ -d "../../layers" ]
then
    # patches
    cd ../meta-freescale
    patch -p1 < ../meta-pasta-demo/patches/meta-freescale/0001-leo-support-wayland-weston-vulkan-etc.patch
    patch -p1 < ../meta-pasta-demo/patches/meta-freescale/0002-leo-add-gobject-introspection-for-gst-plugins-base.i.patch
    cd ../meta-toradex-nxp
    patch -p1 < ../meta-pasta-demo/patches/meta-toradex-nxp/0001-leo-support-wayland-weston.patch
    cd ../meta-toradex-torizon
    patch -p1 < ../meta-pasta-demo/patches/meta-toradex-torizon/0001-leo-changes-to-support-wayland-weston.patch
    cd ../openembedded-core
    patch -p1 < ../meta-pasta-demo/patches/openembedded-core/0001-leo-use-Gstreamer-1.14.4-bindings.patch
    
    # local.conf
    cd ../meta-pasta-demo
    cp conf/local.conf.example ../../build-torizon/conf/local.conf
    echo 'Remember to accept NXP EULA by un-commeting ACCEPT_FSL_EULA="1" from conf/local.conf'

    # bblayers.conf
    sed -i '$d' ../../build-torizon/conf/bblayers.conf
    echo '  ${OEROOT}/layers/meta-pasta-demo \' >> ../../build-torizon/conf/bblayers.conf
    echo '"' >> ../../build-torizon/conf/bblayers.conf
else
    echo "Please run this script from the meta-pasta-demo root directory!"
fi