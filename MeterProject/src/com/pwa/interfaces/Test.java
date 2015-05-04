/*   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.pwa.interfaces;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.xmlbeans.XmlOptions;

import th.co.tsc.export.ExportDocument;
import th.co.tsc.export.ExportDocument.Export.Body.Column;

/**
 * This is a template sample. A nice description would go here.
 */
public class Test {
	/**
	 * Prints out the names in the xml instance conforming to hello.xsd.
	 */
	public static void main(String[] args) throws org.apache.xmlbeans.XmlException, java.io.IOException {
		Test sample = new Test();
		sample.start(args);
	}

	public void start(String[] args) throws org.apache.xmlbeans.XmlException, java.io.IOException {
		for (int i = 0; i < args.length; i++) {
			validate(args[i]);
		}
	}

	public void validate(String filename) throws org.apache.xmlbeans.XmlException, java.io.IOException {
		File f = new File(filename);
		System.out.println("parsing document: " + f);
		ExportDocument doc = ExportDocument.Factory.parse(f);

		ArrayList errors = new ArrayList();
		XmlOptions opts = new XmlOptions();
		opts.setErrorListener(errors);

		if (doc.validate(opts)) {
			System.out.println("document is valid.");

			Column[] cols = doc.getExport().getBody().getColumnArray();
			System.out.println("--------------Display Sample Bean Infomation----------------");

			System.out.println("--Colums");
			for (int i = 0; i < cols.length; i++) {
				Column c = cols[i];
				System.out.println(i + ". [Align=" + c.getAlign() + ", Pattern=" + c.getPattern() + ", Type="
						+ c.getType() + "] Value=" + c.getStringValue());
			}

			String qStr = doc.getExport().getBody().getQuery().getStringValue().trim();
			System.out.println("--Query");
			System.out.println(qStr);

		} else {
			System.out.println("document is invalid!");

			Iterator iter = errors.iterator();
			while (iter.hasNext()) {
				System.out.println(">> " + iter.next());
			}
		}
	}
}
