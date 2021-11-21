package org.newdawn.slick.tiled;

import org.newdawn.slick.SlickException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.Properties;

protected class GroupObject
{
    public int index;
    public String name;
    public String type;
    public int x;
    public int y;
    public int width;
    public int height;
    private String image;
    public Properties props;
    
    public GroupObject(final Element element) throws SlickException {
        this.name = element.getAttribute("name");
        this.type = element.getAttribute("type");
        this.x = Integer.parseInt(element.getAttribute("x"));
        this.y = Integer.parseInt(element.getAttribute("y"));
        this.width = Integer.parseInt(element.getAttribute("width"));
        this.height = Integer.parseInt(element.getAttribute("height"));
        final Element imageElement = (Element)element.getElementsByTagName("image").item(0);
        if (imageElement != null) {
            this.image = imageElement.getAttribute("source");
        }
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
    }
}
