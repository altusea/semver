package me.play;

import static me.play.StrLiterals.PATTERN;

public record Version(
        int major,
        int minor,
        int patch,
        PreReleaseInfo preReleaseInfo,
        BuildInfo buildInfo
) {

    public static Version fromString(String version) {
        int major, minor, patch;
        PreReleaseInfo preReleaseInfo = null;
        BuildInfo buildInfo = null;
        var matcher = PATTERN.matcher(version);
        if (matcher.matches()) {
            var groups = matcher.groupCount();
            major = Integer.parseInt(matcher.group(1));
            minor = Integer.parseInt(matcher.group(2));
            patch = Integer.parseInt(matcher.group(3));
            // pre-release
            String preReleaseStr;
            if ((preReleaseStr = matcher.group(4)) != null) {
                preReleaseInfo = new PreReleaseInfo(preReleaseStr);
            }
            // build meta
            String buildStr;
            if ((buildStr = matcher.group(5)) != null) {
                buildInfo = new BuildInfo(buildStr);
            }
            return new Version(major, minor, patch, preReleaseInfo, buildInfo);

        } else {
            throw new IllegalArgumentException("Invalid version str: " + version);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(major).append(".").append(minor).append(".").append(patch);
        if (preReleaseInfo != null) sb.append("-").append(preReleaseInfo);
        if (buildInfo != null) sb.append("+").append(buildInfo);
        return sb.toString();
    }

    public static void main(String[] args) {
        Version version = Version.fromString("1.2.3");
        System.out.println(version);
        var version2 = Version.fromString("1.2.2-rc.1");
        System.out.println(version2);
    }
}
