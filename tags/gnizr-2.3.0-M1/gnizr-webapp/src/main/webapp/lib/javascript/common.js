/** 
 * detectBrowser is a function adopted from 
 * http://parentnode.org/javascript/javascript-browser-detection-revisited/
 */
function detectBrowser() { 
    var BO = new Object(); 
    BO["ie"]        = false /*@cc_on || true @*/; 
    BO["ie4"]       = BO["ie"] && (document.getElementById == null); 
    BO["ie5"]       = BO["ie"] && (document.namespaces == null) && (!BO["ie4"]); 
    BO["ie6"]       = BO["ie"] && (document.implementation != null) && (document.implementation.hasFeature != null); 
    BO["ie55"]      = BO["ie"] && (document.namespaces != null) && (!BO["ie6"]); 
    BO["ie7"]       = (BO["ie"] && document.implementation != null && document.compatMode != null && window.XMLHttpRequest != null);
    BO["ns4"]       = !BO["ie"] &&  (document.layers != null) &&  (window.confirm != null) && (document.createElement == null); 
    BO["opera"]     = (self.opera != null); 
    BO["gecko"]     = (document.getBoxObjectFor != null); 
    BO["khtml"]     = (navigator.vendor == "KDE"); 
    BO["konq"]      = ((navigator.vendor == 'KDE') || (document.childNodes) && (!document.all) && (!navigator.taintEnabled)); 
    BO["safari"]    = (document.childNodes) && (!document.all) && (!navigator.taintEnabled) && (!navigator.accentColorName); 
    BO["safari1.2"] = (parseInt(0).toFixed == null) && (BO["safari"] && (window.XMLHttpRequest != null)); 
    BO["safari2.0"] = (parseInt(0).toFixed != null) && BO["safari"] && !BO["safari1.2"]; 
    BO["safari1.1"] = BO["safari"] && !BO["safari1.2"] && !BO["safari2.0"]; 
    return BO; 
} 
 
var BO = new detectBrowser(); 