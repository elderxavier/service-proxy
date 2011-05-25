/* Copyright 2009 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.membrane.core.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.predic8.membrane.core.rules.ForwardingRule;
import com.predic8.membrane.core.rules.ProxyRule;
import com.predic8.membrane.core.rules.Rule;

public class Rules extends AbstractXMLElement {

	public static final String ELEMENT_NAME = "rules";

	private Collection<Rule> rules = new ArrayList<Rule>();

	@Override
	protected String getElementName() {
		return ELEMENT_NAME;
	}

	@Override
	protected void parseChildren(XMLStreamReader token, String child) throws XMLStreamException {
		if (ForwardingRule.ELEMENT_NAME.equals(child)) {
			rules.add((ForwardingRule) new ForwardingRule().parse(token));
		} else if (ProxyRule.ELEMENT_NAME.equals(child)) {
			rules.add((ProxyRule) new ProxyRule().parse(token));
		}

	}

	public Collection<Rule> getValues() {
		return rules;
	}

	public void setValues(Collection<Rule> values) {
		this.rules = values;
	}
	
	@Override
	public void write(XMLStreamWriter out) throws XMLStreamException {
		out.writeStartElement(ELEMENT_NAME);
		for (Rule rule : rules) {
			rule.write(out);
		}
		out.writeEndElement();
	}

}