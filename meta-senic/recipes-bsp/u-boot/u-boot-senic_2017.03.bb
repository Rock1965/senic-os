DESCRIPTION="Upstream's U-boot configured for sunxi devices"
LICENSE = "GPLv2"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native"

COMPATIBLE_MACHINE = "(senic-hub-beta|senic-hub)"

SRCBRANCH = "senic/v2017.03"
SRC_URI = "git://git@github.com/getsenic/senic-os-uboot.git;protocol=ssh;branch=${SRCBRANCH}; \
	   file://boot.cmd \
	   "

SRCREV = "2f0e2c9850fb829ba895b5644d89f83a3974d112"
PV = "v2017.03+git${SRCPV}"
S = "${WORKDIR}/git"

SPL_BINARY="u-boot-sunxi-with-spl.bin"

UBOOT_ENV_SUFFIX = "scr"
UBOOT_ENV = "boot"

do_compile_append() {
    ${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/${UBOOT_ENV_BINARY}
}