SUMMARY = "AWS IoT Greengrass Core Software"
DESCRIPTION = "The AWS IoT Greengrass Core software extends AWS functionality \
onto an AWS IoT Greengrass core device, enabling local devices to act locally \
on the data they generate."

LICENSE = "gg-core-sw"

# License is the same, including hash, for all arch
LIC_FILES_CHKSUM = "file://ggc/packages/1.9.2/THIRD-PARTY-LICENSES;md5=53b6a4caa097863bc3971d5e0ac6d1db \
                    file://ggc/packages/1.9.2/LICENSE;md5=cbd8d279038a7a6174c17a9342aadab7 \
                    file://ota/ota_agent_v1.1.1/LICENSE/THIRD-PARTY-LICENSES;md5=632ff94a8185c978475c184e49112276 \
                    file://ota/ota_agent_v1.1.1/LICENSE/LICENSE;md5=cbd8d279038a7a6174c17a9342aadab7 \
					file://greengrass-license-v1.pdf;md5=159dfe7044611ef5ed050148911bc20c"

SRC_URI = " https://d1onfpft10uf5o.cloudfront.net/greengrass-core/downloads/${PV}/greengrass-linux-aarch64-${PV}.tar.gz;name=app-aarch64;subdir=aarch64 \
            https://d1onfpft10uf5o.cloudfront.net/greengrass-core/downloads/${PV}/greengrass-linux-armv7l-${PV}.tar.gz;name=app-arm;subdir=arm \
            https://d1onfpft10uf5o.cloudfront.net/greengrass-core/downloads/${PV}/greengrass-linux-x86-64-${PV}.tar.gz;name=app-x86-64;subdir=x86-64 \
            https://s3-us-west-2.amazonaws.com/greengrass-release-license/greengrass-license-v1.pdf;name=lic;subdir=${TARGET_ARCH}/greengrass"

SRC_URI[app-aarch64.md5sum] = "967cd25ac77e733b0a1b42d83dc96ed1"
SRC_URI[app-aarch64.sha256sum] = "4cbaf91e5354fe49ded160415394413f068426c2bba13089e6b8a28775990a9c"
SRC_URI[lic.md5sum] = "159dfe7044611ef5ed050148911bc20c"
SRC_URI[lic.sha256sum] = "72fd3a70c52cd8563a62c988f7908989728c893f7b77309cc3cb68f6f7b5f754"
SRC_URI[app-arm.md5sum] = "63a1f6aae22260be19f34f278f7e7833"
SRC_URI[app-arm.sha256sum] = "4bc0bc8a938cdb3d846df92e502155c6ec8cbaf1b63dfa9f3cc3a51372d95af5"
SRC_URI[app-x86-64.md5sum] = "a1f90898cc324b3c2a6a7cf50038d70a"
SRC_URI[app-x86-64.sha256sum] = "ee55e370491905415cfab962f5429228c58aec4c7f481e66a14e952e1b62fe36"

S = "${WORKDIR}/${TARGET_ARCH}/greengrass"

do_install () {
	install -d ${D}/greengrass
	cp -R --no-dereference --preserve=mode,links -v ${S}/* ${D}/greengrass
	chown -R root:root ${D}/greengrass
}

# The binaries are provided by AWS, nothing we can do about it
INSANE_SKIP_${PN} = "already-stripped ldflags"
FILES_${PN} += "/greengrass/*"