DESCRIPTION = "The AWS IoT Greengrass Core SDK is meant to be used by AWS \
Lambda functions running on an AWS IoT Greengrass Core. It will enable Lambda \
functions to invoke other Lambda functions deployed to the Greengrass Core, \
publish messages to the Greengrass Core and work with the local Shadow service."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=8e8ca69494153ec22415484d6163e614"

SRC_URI[md5sum] = "24e649b05a9ec25f4dc5e26076cc8e12"
SRC_URI[sha256sum] = "fcaca111236b684b42c3fdff9568ab1f86505ead9f488a2df35bf4cd20547bf4"

PYPI_PACKAGE = "greengrasssdk"

inherit pypi

RDEPENDS_${PN} += "\
    aws-iot-greengrass-core-software \
    ${PYTHON_PN}-modules \
    "