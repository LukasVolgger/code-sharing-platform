<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="/styles/style.css" />
  <link rel="stylesheet" href="/highlightjs/styles/default.min.css">
  <script src="/highlightjs/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
  <title>Latest</title>
</head>
<body>
  <#list codeSnippetList as codeSnippet>
    <div>
      <pre>
          <code>${codeSnippet.getCode()}</code>
      </pre>
      <span>${codeSnippet.getDate()}</span>
    </div>
  </#list>
</body>
</html>