/**
 * Copyright (c) 2000-2012 Lemansys, Ltd. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package org.lemansys.xlews.editors.xlem;

public class XLemConstants {

	public static final String[] TAGS = {

		"page","/page",
        "content","/content",
        "dim","dim <varname> as <vartype>","set",
        "if","/if","endif","else","elseif",
        "for","forall","next","/for","/next","endfor","endforall","/forall",
        "while","wend","/while","endwhile",
        "try","/try","endtry","catch","/catch","endcatch","finally","endfinally",
        "call","global","class",
        "function","/function","endfunction","external",
        "method","/method","endmethod","field","fields","/fields","endfields",
        "model","/model","endmodel",
        "switchout","/switchout","uselibrary","return",
        "websocket:onmessage","/websocket:onmessage",
        "websocket:open","/websocket:open", "websocket:close","/websocket:close",
        "websocket","/websocket",
        "ajax:action","/ajax:action",
        "ui:activate","ui:calendar","ui:form","ui:panel","ui:section","ui:tab","ui:tabs",
        "/ui:calendar","/ui:form","/ui:panel","/ui:tabs", "/ui:section", "/ui:tabs"

	};

	public static final String HTML_TAGS[] = {

	"!DOCTYPE", "A", "ABBR", "ACRONYM", "ADDRESS", "APPLET", "AREA",
			"article5", "aside5", "audio5", "B", "BASE", "BASEFONT", "BDO",
			"BGSOUND", "BIG", "BLINK", "BLOCKQUOTE", "BODY", "BR/", "BUTTON",
			"canvas5", "CAPTION", "CENTER", "CITE", "CODE", "COL", "COLGROUP",
			"command5", "datalist5", "DD", "DEL", "details5", "DFN", "DIR",
			"DIV", "DL", "DT", "EM", "EMBED", "eventsource5", "FIELDSET",
			"figcaption5", "figure5", "footer5", "FONT", "FORM", "FRAME",
			"FRAMESET", "H1", "H2", "H3", "H4", "H5", "H6", "HEAD", "header5",
			"hgroup5", "HR/", "HTML", "I", "IFRAME", "ILAYER", "IMG/",
			"INPUT/", "INS", "ISINDEX", "KBD", "KEYGEN", "LABEL", "LAYER",
			"LEGEND", "LI", "LINK", "LISTING", "MAP", "mark5", "MENU", "META/",
			"meter5", "MULTICOL", "nav5", "NOBR", "NOEMBED", "NOFRAMES",
			"NOLAYER", "NOSCRIPT", "OBJECT", "OL", "OPTGROUP", "OPTION",
			"output5", "P", "PARAM", "PLAINTEXT", "PRE", "progress5", "Q",
			"RUBY", "rp5", "rt5", "S", "SAMP", "SCRIPT", "section5", "SELECT",
			"SERVER", "SMALL", "SOUND", "source5", "SPACER", "SPAN", "STRIKE",
			"STRONG", "STYLE", "SUB", "summary5", "SUP", "TABLE", "TBODY",
			"TEXTAREA", "TD", "TFOOT", "TH", "THEAD", "time5", "TITLE", "TR",
			"TT", "U", "UL", "VAR", "video5", "WBR", "XMP",

			"/A", "/ABBR", "/ACRONYM", "/ADDRESS", "/APPLET", "/AREA",
			"/article5", "/aside5", "/audio5", "/B", "/BASE", "/BASEFONT",
			"/BDO", "/BGSOUND", "/BIG", "/BLINK", "/BLOCKQUOTE", "/BODY",
			"/BUTTON", "/canvas5", "/CAPTION", "/CENTER", "/CITE", "/CODE",
			"/COL", "/COLGROUP", "/command5", "/datalist5", "/DD", "/DEL",
			"/DFN", "/DIR", "/DIV", "/DL", "/DT", "/EM", "/EMBED",
			"/eventsource5", "/FIELDSET", "/figcaption5", "/figure5",
			"/footer5", "/FONT", "/FORM", "/FRAME", "/FRAMESET", "/H1", "/H2",
			"/H3", "/H4", "/H5", "/H6", "/HEAD", "/header5", "/hgroup5",
			"/HTML", "/I", "/IFRAME", "/ILAYER", "/INS", "/ISINDEX", "/KBD",
			"/KEYGEN", "/LABEL", "/LAYER", "/LEGEND", "/LI", "/LINK",
			"/LISTING", "/MAP", "/mark5", "/MENU", "/meter5", "/MULTICOL",
			"/nav5", "/NOBR", "/NOEMBED", "/NOFRAMES", "/NOLAYER", "/NOSCRIPT",
			"/OBJECT", "/OL", "/OPTGROUP", "/OPTION", "/output5", "/P",
			"/PARAM", "/PLAINTEXT", "/PRE", "/progress5", "/Q", "/RUBY",
			"/rp5", "/rt5", "/S", "/SAMP", "/SCRIPT", "/section5", "/SELECT",
			"/SERVER", "/SMALL", "/SOUND", "/source5", "/SPACER", "/SPAN",
			"/STRIKE", "/STRONG", "/STYLE", "/SUB", "/summary5", "/SUP",
			"/TABLE", "/TBODY", "/TEXTAREA", "/TD", "/TFOOT", "/TH", "/THEAD",
			"/time5", "/TITLE", "/TR", "/TT", "/U", "/UL", "/VAR", "/video5",
			"/WBR", "/XMP"

	};

	public static final String[] XSL_TAGS = {

	"xsl:apply-imports/", "xsl:apply-templates", "xsl:attribute",
			"xsl:attribute-set", "xsl:call-template", "xsl:choose",
			"xsl:comment", "xsl:copy", "xsl:copy-of/", "xsl:decimal-format/",
			"xsl:element", "xsl:fallback", "xsl:for-each", "xsl:if",
			"xsl:import/", "xsl:include/", "xsl:key/", "xsl:message",
			"xsl:namespace-alias/", "xsl:number/", "xsl:otherwise", "xsl:output/",
			"xsl:param", "xsl:preserve-space/", "xsl:processing-instruction",
			"xsl:sort/", "xsl:strip-space/", "xsl:stylesheet", "xsl:template",
			"xsl:text", "xsl:transform", "xsl:value-of/", "xsl:variable",
			"xsl:when", "xsl:with-param", "/xsl:apply-imports",
			"/xsl:apply-templates", "/xsl:attribute", "/xsl:attribute-set",
			"/xsl:call-template", "/xsl:choose", "/xsl:comment", "/xsl:copy",
			"/xsl:copy-of", "/xsl:decimal-format", "/xsl:element",
			"/xsl:fallback", "/xsl:for-each", "/xsl:if", "/xsl:import",
			"/xsl:include", "/xsl:key", "/xsl:message", "/xsl:namespace-alias",
			"/xsl:number", "/xsl:otherwise", "/xsl:output", "/xsl:param",
			"/xsl:preserve-space", "/xsl:processing-instruction", "/xsl:sort",
			"/xsl:strip-space", "/xsl:stylesheet", "/xsl:template",
			"/xsl:text", "/xsl:transform", "/xsl:value-of", "/xsl:variable",
			"/xsl:when", "/xsl:with-param" };

	public static final String[] LEM_FUNCTIONS = {

	"as", "createonfail",

	"decode64",
	
	"encode64",
	
	"false",

	"htmlformat", "htmlformatspecial",

	"in", 
	
	"is_android", "is_blackberry",
	
	"is_ipad",	"is_iphone",
	
	"is_mobile",
	
	"is_windows_ce", "is_windows_mobile", 

	
	"isblank", "ismember",

	"ismobile",
	
	"isnotblank","isnotnull","isnull",

	"javatype",
	
	"multithread",

	"new", "newjavatype", "notisnull", "null",

	"src", "sqlformat", "step", "sum",

	"to", "toboolean", "todouble", "tofloat", "tointeger", "tolong",
	"toMD5",
	"tostring", "toURI",

	"true",

	"version"

	};
	
	
	public static final String[] LEM_PAGE_PREPARED_INSTANCES= {
		
		  "application",
		  "config",
		  "exception",
		  "pageContext",
		  "querystring",
		  "request",
		  "response",
		  "session",
		  "this"
		  
		
		
	};
	
	public static final String[] LEM_PAGE_VARIABLES= {
		
		
		 "string",
         "integer",
         "double",
         "boolean",
         "float",
         "long",
         "date",
         "buffer",

         "genericdatabase",
         
         "textparser",
         
         "xmlparser",
         "xmldocument",
         "xmlbuilder"
		
	};

}
