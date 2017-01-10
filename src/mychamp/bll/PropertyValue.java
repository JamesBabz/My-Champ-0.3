package mychamp.bll;

/**
 *
 * @author Thomas Meyer Hansen, Simon Juhl Birkedal, Stephan Fuhlendorff & Jacob
 * Enemark
 */
public enum PropertyValue
{
    NAME("name"),
    PLAYED("played"),
    WON("won"),
    DRAW("draw"),
    LOST("lost"),
    GF("goalFor"),
    GA("goalAgainst"),
    POINTS("point");

    private final String PropertyValue;

    /**
     * An enum 'constructor'?
     *
     * @param PropertyValue
     */
    PropertyValue(String PropertyValue)
    {
        this.PropertyValue = PropertyValue;
    }

    @Override
    public String toString()
    {
        return PropertyValue;
    }
}
