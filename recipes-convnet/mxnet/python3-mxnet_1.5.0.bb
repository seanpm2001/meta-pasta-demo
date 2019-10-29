DESCRIPTION = "Lightweight, Portable, Flexible Distributed/Mobile Deep \
Learning with Dynamic, Mutation-aware Dataflow Dep Scheduler; for Python, R, \
Julia, Scala, Go, Javascript and more"
SUMMARY = "Apache MXNet (incubating) for Deep Learning"
HOMEPAGE = "https://mxnet.apache.org/"
SECTION = "libs"
LICENSE = "Apache-2.0"

DEPENDS = " mxnet "

# graphviz-python 
RDEPENDS_${PN} = " mxnet graphviz python3-numpy python3-requests python3-threading python3-ctypes "

LIC_FILES_CHKSUM = "file://README.md;md5=4c95166f0d137c08eec26b70bda30031"

SRC_URI = " gitsm://github.com/apache/incubator-mxnet.git;protocol=https;branch=v1.5.x "
# Release 1.5.0
SRCREV = "75a9e187d00a8b7ebc71412a02ed0e3ae489d91f"

S = "${WORKDIR}/git/python"

do_configure_prepend() {
    #cp ${WORKDIR}/recipe-sysroot/${libdir}/libmxnet.so ${S}/mxnet/libmxnet.so
    ln -s ${WORKDIR}/recipe-sysroot/${libdir}/libmxnet.so ${S}/mxnet/libmxnet.so
}

do_install_append() {
    rm -r ${D}${datadir}

    install -d ${D}${libdir}/python3.7/site-packages/mxnet
    ln -s ${libdir}/libmxnet.so ${D}${libdir}/python3.7/site-packages/mxnet/libmxnet.so
}

FILES_${PN} += " ${libdir}/python3.7/site-packages/mxnet/libmxnet.so "

INSANE_SKIP_${PN} += "dev-so"

inherit setuptools3