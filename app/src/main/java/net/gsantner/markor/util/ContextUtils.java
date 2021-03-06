/*#######################################################
 *
 *   Maintained by Gregor Santner, 2017-
 *   https://gsantner.net/
 *
 *   License: Apache 2.0 / Commercial
 *  https://github.com/gsantner/opoc/#licensing
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
#########################################################*/
package net.gsantner.markor.util;

import android.content.Context;
import android.webkit.MimeTypeMap;

import net.gsantner.markor.App;
import net.gsantner.markor.format.markdown.MarkdownTextConverter;

import java.io.File;
import java.util.Locale;

public class ContextUtils extends net.gsantner.opoc.util.ContextUtils {
    public ContextUtils(Context context) {
        super(context);
    }

    public static ContextUtils get() {
        return new ContextUtils(App.get());
    }

    public static String getMimeType(String url) {
        String mime = null;
        String ext = MimeTypeMap.getFileExtensionFromUrl(url);
        return ext != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext) : null;
    }

    // Either pass file or null and absolutePath
    public boolean isMaybeMarkdownFile(File file, String... absolutePath) {
        String path = (absolutePath != null && absolutePath.length > 0)
                ? absolutePath[0] : file.getAbsolutePath();
        path = path.toLowerCase(Locale.ROOT);
        for (String ext : MarkdownTextConverter.MD_EXTENSIONS) {
            if (path.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
