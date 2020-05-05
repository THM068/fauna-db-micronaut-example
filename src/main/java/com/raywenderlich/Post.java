package com.raywenderlich;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.faunadb.client.query.Expr;
import com.faunadb.client.query.Language;
import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;
@Introspected
public class Post implements Serializable {

    @JsonProperty("title")
    private String title;
    @JsonProperty("summary")
    private String summary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Expr toFauna()  {
        return Language.Obj(
                "data", Language.Obj(
                        "title", Language.Value(this.title),
                        "summary", Language.Value(this.summary))
        );
    }

}
