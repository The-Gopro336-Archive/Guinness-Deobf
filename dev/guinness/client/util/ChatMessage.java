package dev.guinness.client.util;

import net.minecraft.util.text.ITextComponent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.text.TextComponentBase;

public class ChatMessage extends TextComponentBase
{
    public String text;
    
    public ChatMessage(final String text) {
        final Pattern pattern = Pattern.compile("&[0123456789abcdefrlosmk]");
        final Matcher matcher = pattern.matcher(text);
        final StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            final String replacement = "§" + matcher.group().substring(1);
            matcher.appendReplacement(stringBuffer, replacement);
        }
        matcher.appendTail(stringBuffer);
        this.text = stringBuffer.toString();
    }
    
    public String func_150261_e() {
        return this.text;
    }
    
    public ITextComponent func_150259_f() {
        return new ChatMessage(this.text);
    }
}
