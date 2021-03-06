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
public class StatusColumn implements TraceabilityColumn {

	protected static final String LABEL = "Status";
	protected static final String KEY = "status";

	protected StatusColumn() {
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	@Override
	public void addResult(State state, Cell cell, JsonObject o) {
		JsonPrimitive primitive = o.getAsJsonPrimitive(KEY);
		String status = null == primitive ? null : primitive.getAsString();

		RichTextString richTextString = new XSSFRichTextString(status);
		cell.setCellValue(richTextString);
		state.setStatus(cell, status);
	}
}
