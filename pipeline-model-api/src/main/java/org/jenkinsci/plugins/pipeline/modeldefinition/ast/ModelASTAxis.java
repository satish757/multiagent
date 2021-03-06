package org.jenkinsci.plugins.pipeline.modeldefinition.ast;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.jenkinsci.plugins.pipeline.modeldefinition.validator.ModelValidator;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 *
 * @author Liam Newman
 */
public class ModelASTAxis extends ModelASTElement {

    private ModelASTKey name;
    private List<ModelASTValue> values = new ArrayList<>();

    public ModelASTAxis(Object sourceLocation) {
        super(sourceLocation);
    }

    @Override
    @Nonnull
    public JSONObject toJSON() {
        return new JSONObject()
                .accumulate("name", toJSON(name))
                .accumulate("values", toJSONArray(values));
    }

    @Override
    public void validate(@Nonnull ModelValidator validator) {
        validator.validateElement(this);
    }

    @Override
    @Nonnull
    public String toGroovy() {
        StringBuilder argStr = new StringBuilder()
            .append("name '").append(toGroovy(name) + "'\n")
            .append(("values ")).append(toGroovyArgList(values)).append("\n");
        return "axis {\n" + argStr.toString() + "}\n";
    }

    @Override
    public void removeSourceLocation() {
        super.removeSourceLocation();
        removeSourceLocationsFrom(values, name);
    }

    @Override
    public String toString() {
        return "ModelASTAxis{" +
                "name=" + name +
                "values=" + values +
                "}";
    }

    public ModelASTKey getName() {
        return name;
    }

    public void setName(ModelASTKey name) {
        this.name = name;
    }

    public List<ModelASTValue> getValues() {
        return values;
    }

    public void setValues(List<ModelASTValue> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelASTAxis)) return false;
        if (!super.equals(o)) return false;
        ModelASTAxis that = (ModelASTAxis) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getValues(), that.getValues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getValues());
    }
}
