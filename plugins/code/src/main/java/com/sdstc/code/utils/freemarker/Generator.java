package com.sdstc.code.utils.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import com.sdstc.code.utils.Params;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class Generator {

	public void genarator(Object table, String outPath,String fileName,String ftlPath) throws Exception {
		File path=new File(outPath);
		if(!path.exists()) {
			path.mkdirs();
		}
		
		Configuration configuration = new Configuration(Configuration.getVersion());
		TemplateLoader ctl = new ClassTemplateLoader(GeBaseDao.class, "/");
		configuration.setTemplateLoader(ctl);
		Template template = configuration.getTemplate(ftlPath);
		Writer out = new FileWriter(new File(outPath+Params.fileSeq+fileName));
		template.process(table, out);
		out.close();
	}
}
