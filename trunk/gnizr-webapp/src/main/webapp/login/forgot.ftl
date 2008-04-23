<#include "/lib/web/macro-lib.ftl"/>

<@pageBegin pageTitle="login" 
            cssHref=[gzUrl("/css/gnizr-frontpage.css")]
            enableJS=false/>                      

<div class="frontPage">   
       

<div id="siteBanner">
  <h1 class="siteName">${getSiteName()}</h1>
  <h2 class="siteDescription">${getSiteDescription()}</h2>
</div>

<div class="frontPageMessage">
<@pageTitle>Reset Password</@pageTitle>
<p>
To reset your password, provide your username.
</p>
<@displayActionError action=action/>
<@ww.form namespace="/password" action="requestReset.action" method="post">
<@ww.textfield label="Username" name="username"/>
<@ww.submit cssClass="btn" value="Submit"/>
</@ww.form>
</div>

<div class="cleardiv"/>
<@pageEnd/>