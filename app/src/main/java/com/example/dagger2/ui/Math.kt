package com.example.dagger2.ui
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

@Composable
fun MathFormulaView(
    latex: String,
    fontSize: Float = 16f,
    textColor: String = "#FFFFFF",
    backgroundColor: String = "transparent",
    displayMode: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
) {
    // Экранирование LaTeX для предотвращения XSS
    val escapedLatex = latex.replace("`", "\\`").replace("\$", "\\\$")

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                // Настройки безопасности
                settings.apply {
                    javaScriptEnabled = true // Необходимо для KaTeX
                    allowFileAccess = false
                    allowContentAccess = false
                    allowUniversalAccessFromFileURLs = false
                }
                setBackgroundColor(0x00000000) // Прозрачный фон

                // Загрузка HTML с локальными ресурсами
                loadDataWithBaseURL(
                    "file:///android_asset/",
                    """
                    <!DOCTYPE html>
                    <html>
                    <head>
                      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                      <link rel="stylesheet" href="file:///android_asset/katex/katex.min.css">
                      <script src="file:///android_asset/katex/katex.min.js"></script>
                      <style>
                        body {
                          margin: 0;
                          padding: 0;
                          background-color: $backgroundColor;
                          display: flex;
                          justify-content: center;
                          align-items: center;
                          height: 100%;
                          font-size: ${fontSize}px;
                          color: $textColor;
                          white-space: pre;
                        }
                        #math {
                          padding: 4px;
                        }
                      </style>
                    </head>
                    <body>
                      <div id="math"></div>
                      <script>
                        try {
                          katex.render(String.raw`${escapedLatex}`, document.getElementById('math'), {
                            throwOnError: true,
                            displayMode: $displayMode
                          });
                        } catch (e) {
                          document.getElementById('math').innerText = 'Error: ' + e.message;
                        }
                      </script>
                    </body>
                    </html>
                    """.trimIndent(),
                    "text/html",
                    "utf-8",
                    null
                )
            }
        },
        modifier = modifier
    )
}