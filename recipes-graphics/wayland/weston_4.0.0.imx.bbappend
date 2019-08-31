FILESEXTRAPATHS_prepend := "${THISDIR}/weston:"

SRC_URI_append = " file://weston-pasta-demo.ini "

do_install_prepend () {
    mv ${WORKDIR}/weston-pasta-demo.ini ${WORKDIR}/weston.ini
}