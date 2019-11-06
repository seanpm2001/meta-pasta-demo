FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_apalis-imx8 = " \
    file://0001-pasta-demo-use-old-device-tree-name.patch \
"