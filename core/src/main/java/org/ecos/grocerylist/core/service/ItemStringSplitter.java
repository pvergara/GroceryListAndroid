package org.ecos.grocerylist.core.service;

import org.ecos.grocerylist.core.exceptions.SplitterException;
import org.ecos.grocerylist.core.items.ItemPart;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemStringSplitter {
    public static Map<ItemPart, String> split(String itemString) throws SplitterException {
        final Pattern p = Pattern.compile("^(\\w+|\\w+\\s\\w+)\\s?([+|-])?([0-9]+)?");
        return new HashMap<ItemPart, String>() {{
            Matcher m = p.matcher(itemString);
            if (m.matches()) {
                put(ItemPart.name, m.group(1));
                if (m.groupCount() == 3 && m.group(2) != null && m.group(3) != null) {
                    put(ItemPart.incrementType, m.group(2));
                    put(ItemPart.quantity, m.group(3));
                }
            }else
                throw new SplitterException();
        }};
    }
}
