<!doctype html>
<html>
<head>
    <link href="/style.css" rel="stylesheet"/>
</head>
<body>
<div class="box">
    <form method="get" action="/">
        <h1>Favicon Application</h1>
        <hr/>
        <ul class="form-style-1">
            <li>
                <label for="website">Website<span class="required">*</span>
                    <input class="field-long" id="website" type="text" name="url"/>
            </li>
            <li>
                <label for="refresh">Refresh Value?
                <input type="checkbox" name="refresh" value="true">
            </li>
            <li><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></li>
        </ul>
    </form>
</div>
<div class="box">
    <#if faviconUrl??>
        <h3>Site Favicon:</h3>
        <img src="${faviconUrl}" class="im-center"/>
    <#else>
        `<h3>No Favicon Found!</h3>
    </#if>
</div>
</body>
</html>