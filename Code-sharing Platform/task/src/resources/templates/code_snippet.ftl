<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="/styles/style.css" />
  <link rel="stylesheet" href="/highlightjs/styles/default.min.css">
  <script src="/highlightjs/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
  <title>Code</title>
</head>
<body>
  <pre id="code_snippet">
    <code>${codeSnippet.getCode()}</code>
  </pre>
  <span id="load_date">${codeSnippet.getDate()}</span>

  <#if codeSnippet.isHasViewsRestriction()>
    <span id="views_restriction">${codeSnippet.getViews()}</span>
  </#if>
  <#if codeSnippet.isHasTimeRestriction()>
    <span id="time_restriction">${codeSnippet.getTime()}</span>
  </#if>

</body>
</html>