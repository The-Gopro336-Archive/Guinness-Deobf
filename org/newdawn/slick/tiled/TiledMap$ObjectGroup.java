package org.newdawn.slick.tiled;

import org.newdawn.slick.SlickException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.Properties;
import java.util.ArrayList;

protected class ObjectGroup
{
    public int index;
    public String name;
    public ArrayList objects;
    public int width;
    public int height;
    public Properties props;
    
    public ObjectGroup(final Element element) throws SlickException {
        this.name = element.getAttribute("name");
        this.width = Integer.parseInt(element.getAttribute("width"));
        this.height = Integer.parseInt(element.getAttribute("height"));
        this.objects = new ArrayList();
        final Element propsElement = (Element)element.getElementsByTagName("properties").item(0);
        if (propsElement != null) {
            final NodeList properties = propsElement.getElementsByTagName("property");
            if (properties != null) {
                this.props = new Properties();
                for (int p = 0; p < properties.getLength(); ++p) {
                    final Element propElement = (Element)properties.item(p);
                    final String name = propElement.getAttribute("name");
                    final String value = propElement.getAttribute("value");
                    this.props.setProperty(name, value);
                }
            }
        }
        final NodeList objectNodes = element.getElementsByTagName("object");
        for (int i = 0; i < objectNodes.getLength(); ++i) {
            final Element objElement = (Element)objectNodes.item(i);
            final GroupObject object = new GroupObject(objElement);
            object.index = i;
            this.objects.add(object);
        }
    }
}
