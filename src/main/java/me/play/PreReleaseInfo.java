package me.play;

import org.apache.commons.lang3.StringUtils;

public record PreReleaseInfo(String literal)  {

    @Override
    public String toString() {
        return StringUtils.defaultString(literal);
    }
}
