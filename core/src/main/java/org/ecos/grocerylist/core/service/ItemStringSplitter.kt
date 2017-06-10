package org.ecos.grocerylist.core.service

import org.ecos.grocerylist.core.exceptions.SplitterException
import org.ecos.grocerylist.core.items.ItemPart
import java.util.*
import java.util.regex.Pattern

object ItemStringSplitter {
    val itemsNameONEWordONLY = Pattern.compile("^([a-zA-Z]+)$")!!
    private val itemsNameONEWordAndQuantity = Pattern.compile("^([a-zA-Z]+)\\sx(\\d)$")!!
    private val itemsNameMOREThanOneWordONLY = Pattern.compile("^([a-zA-Z]+\\s[a-zA-Z]+)$")!!
    private val itemsNameMOREThanOneWordANDQuantity = Pattern.compile("^([a-zA-Z]+[\\s[a-zA-Z]+]+)\\sx(\\d)$")!!

    private var matcher = itemsNameONEWordONLY.matcher("")

    private val groupIndexItemsQuantity = 2
    private val groupIndexItemsName = 1

    @Throws(SplitterException::class)
    fun split(itemString: String): Map<ItemPart, String> {
        val result = HashMap<ItemPart, String>()

        when {
            hasOnlyAWord(itemString) -> generateResultOnlyItemsName(result)
            hasOnlyOneWordAndEndsWithQuantity(itemString) -> generateResultWithItemsNameAndQuantity(result)
            hasMoreThanOneAWord(itemString) -> generateResultOnlyItemsName(result)
            hasMoreThanOneAWordAndEndsWithQuantity(itemString) -> generateResultWithItemsNameManyWordsAndQuantity(result)
            else -> throw SplitterException()
        }

        return result
    }

    private fun hasOnlyAWord(itemString: CharSequence): Boolean {
        matcher = itemsNameONEWordONLY.matcher(itemString)
        return matcher.matches()
    }

    private fun hasOnlyOneWordAndEndsWithQuantity(itemString: String): Boolean {
        matcher = itemsNameONEWordAndQuantity.matcher(itemString)
        return matcher.matches()
    }

    private fun hasMoreThanOneAWord(itemString: String): Boolean {
        matcher = itemsNameMOREThanOneWordONLY.matcher(itemString)
        return matcher.matches()
    }

    private fun hasMoreThanOneAWordAndEndsWithQuantity(itemString: String): Boolean {
        matcher = itemsNameMOREThanOneWordANDQuantity.matcher(itemString)
        return matcher.matches()
    }

    private fun generateResultOnlyItemsName(result: HashMap<ItemPart, String>) {
        result.put(ItemPart.name, matcher.group(groupIndexItemsName))
    }

    private fun generateResultWithItemsNameAndQuantity(result: HashMap<ItemPart, String>) {
        result.put(ItemPart.name, matcher.group(groupIndexItemsName))
        result.put(ItemPart.quantity, matcher.group(groupIndexItemsQuantity))
    }

    private fun generateResultWithItemsNameManyWordsAndQuantity(result: HashMap<ItemPart, String>) {
        result.put(ItemPart.name, matcher.group(groupIndexItemsName))
        result.put(ItemPart.quantity, matcher.group(groupIndexItemsQuantity))
    }
}