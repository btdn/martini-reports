/*
Copyright 2017 Penny Rohr Curich

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package guru.qas.martini.report.column;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import guru.qas.martini.report.State;

@SuppressWarnings("WeakerAccess")
@Component
public class ThreadColumn implements TraceabilityColumn {

	protected static final String LABEL = "Thread Group/Name";
	protected static final String KEY_GROUP = "threadGroup";
	protected static final String KEY_NAME = "thread";

	protected ThreadColumn() {
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	@Override
	public void addResult(State state, Cell cell, JsonObject o) {
		JsonPrimitive primitive = o.getAsJsonPrimitive(KEY_GROUP);
		String group = null == primitive ? "" : primitive.getAsString();

		primitive = o.getAsJsonPrimitive(KEY_NAME);
		String thread = null == primitive ? "" : primitive.getAsString();

		String value = group.isEmpty() ? thread : String.format("%s %s", group, thread);

		RichTextString richTextString = new XSSFRichTextString(value);
		cell.setCellValue(richTextString);
	}
}
