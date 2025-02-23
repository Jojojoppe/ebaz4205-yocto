SUMMARY = "Boot image for EBAZ4205 using prebuilt FSBL, system.bit and u-boot"
LICENSE = "CLOSED"

# Depend on the u-boot-xlnx recipe so its u-boot.elf is built
DEPENDS += " u-boot-xlnx zynq-mkbootimage-native"

# Include the prebuilt FSBL and bitstream files in this recipe’s files directory
SRC_URI = "file://fsbl.elf \
           file://system.bit \
           file://boot.bif \
          "

# Set the working directory
S = "${WORKDIR}"

# Optionally, define where u-boot.elf is deployed by u-boot-xlnx.
# You might adjust this to match your u-boot-xlnx deploy location.
DEPLOYDIR_U_BOOT ?= "${DEPLOY_DIR_IMAGE}"

# Task to build the boot image
do_bootimage () {
    # 1. Copy u-boot.elf from the u-boot-xlnx deploy directory to WORKDIR
    UBIN="${DEPLOYDIR_U_BOOT}/u-boot.elf"
    if [ ! -f "${UBIN}" ]; then
        die "u-boot.elf not found at ${UBIN}"
    fi
    cp ${UBIN} ${WORKDIR}/u-boot.elf || die "Failed to copy u-boot.elf"

    # 2. Ensure fsbl.elf and system.bit are in WORKDIR (they come from SRC_URI)
    if [ ! -f "${WORKDIR}/fsbl.elf" ]; then
        die "fsbl.elf not found in ${WORKDIR}"
    fi
    if [ ! -f "${WORKDIR}/system.bit" ]; then
        die "system.bit not found in ${WORKDIR}"
    fi

    # 3. Make sure boot.bif exists (either provided via SRC_URI or generated)
    if [ ! -f "${WORKDIR}/boot.bif" ]; then
        die "boot.bif not found in ${WORKDIR}"
    fi

    # 4. Run bootgen to generate BOOT.BIN from boot.bif
    cd ${WORKDIR} && ${STAGING_BINDIR_NATIVE}/mkbootimage boot.bif BOOT.bin || die "bootgen failed"
}
do_bootimage[nostamp] = "1"
do_bootimage[depends] += "u-boot-xlnx:do_deploy"
# Ensure bootimage is built before installation.
addtask do_bootimage after do_compile before do_install

# Install BOOT.bin into the sysconfdir so it gets packaged
do_install () {
    install -d ${D}${sysconfdir}/bootimage
    install -m 0644 ${WORKDIR}/BOOT.bin ${D}${sysconfdir}/bootimage/BOOT.bin
}
FILES_${PN} += "${sysconfdir}/bootimage"

do_deploy () {
    install -d ${DEPLOY_DIR_IMAGE}
    cp ${WORKDIR}/BOOT.bin ${DEPLOY_DIR_IMAGE}/BOOT.bin || die "Failed to deploy BOOT.bin"
}
do_deploy[nostamp] = "1"
# Run deploy after installation.
addtask do_deploy after do_install