<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="/styles/style.css" />
  <link rel="stylesheet" href="/highlightjs/styles/default.min.css">
  <script src="/highlightjs/highlight.min.js"></script>
  <script>hljs.initHighlightingOnLoad();</script>
  <title>Create</title>
</head>
<body>
  <form method="POST" onsubmit="" class="form">
    <label for="code_snippet">Add code: </label>
    <textarea id="code_snippet"></textarea>

    <label for="time_restriction">Time restriction</label>
    <input id="time_restriction" type="text"/>

    <label for="views_restriction">Views restriction</label>
    <input id="views_restriction" type="text"/>

    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
  </form>

  <script>
    function send() {
      let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
      };

      let json = JSON.stringify(object);

      let xhr = new XMLHttpRequest();
      xhr.open("POST", '/api/code/new', false)
      xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
      xhr.send(json);

      if (xhr.status === 200) {
        alert("Success!");
      }
    }
  </script>
</body>
</html>