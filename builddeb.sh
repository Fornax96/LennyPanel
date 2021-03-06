#!/bin/sh

PACKAGE_NAME="lennypanel"
PACKAGE_VERSION="1.0"
SOURCE_DIR=$PWD
TEMP_DIR="/tmp"

mkdir -p $TEMP_DIR/debian/DEBIAN
mkdir -p $TEMP_DIR/debian/lib
mkdir -p $TEMP_DIR/debian/bin
mkdir -p $TEMP_DIR/debian/usr/share/applications
mkdir -p $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME

echo "Package: $PACKAGE_NAME" > $TEMP_DIR/debian/DEBIAN/control
echo "Version: $PACKAGE_VERSION" >> $TEMP_DIR/debian/DEBIAN/control
cat debian/control >> $TEMP_DIR/debian/DEBIAN/control

cp debian/lennypanel.desktop $TEMP_DIR/debian/usr/share/applications/
cp debian/copyright $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/

cp -r dist/ $TEMP_DIR/debian/lib/$PACKAGE_NAME

cp res/lennypanel.png $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/lennypanel.png
chmod 644 $TEMP_DIR/debian/usr/share/doc/$PACKAGE_NAME/lennypanel.png
chmod 644 $TEMP_DIR/debian/lib/$PACKAGE_NAME/res/*

echo '#!/bin/sh' > $TEMP_DIR/debian/bin/lennypanel
echo "export CLASSPATH=$CLASSPATH:/lib/$PACKAGE_NAME" >> $TEMP_DIR/debian/bin/lennypanel
echo "cd /lib/$PACKAGE_NAME/" >> $TEMP_DIR/debian/bin/lennypanel
echo "java -jar LennyPanel.jar $1" >> $TEMP_DIR/debian/bin/lennypanel
chmod 755 $TEMP_DIR/debian/bin/lennypanel

PACKAGE_SIZE=`du -bs $TEMP_DIR/debian | cut -f 1`
PACKAGE_SIZE=$((PACKAGE_SIZE/1024))
echo "Installed-Size: $PACKAGE_SIZE" >> $TEMP_DIR/debian/DEBIAN/control

chown -R root $TEMP_DIR/debian/
chgrp -R root $TEMP_DIR/debian/

cd $TEMP_DIR/
dpkg --build debian
mv debian.deb $SOURCE_DIR/$PACKAGE_NAME-$PACKAGE_VERSION.deb
rm -r $TEMP_DIR/debian
