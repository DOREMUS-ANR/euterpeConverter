package org.doremus.euterpeConverter.musResource;

import org.apache.jena.vocabulary.RDF;
import org.doremus.euterpeConverter.sources.Compositeur;
import org.doremus.euterpeConverter.sources.Evenement;
import org.doremus.euterpeConverter.sources.Oeuvre;
import org.doremus.ontology.CIDOC;
import org.doremus.ontology.FRBROO;
import org.doremus.ontology.MUS;

public class F28_ExpressionCreation extends DoremusResource {

  public F28_ExpressionCreation(Oeuvre oeuvre) {
    // Creation of a Work
    super(oeuvre.getId());
    this.setClass(FRBROO.F28_Expression_Creation);

    int ipCount = 0;
    String function = oeuvre.compositeur.size() > 1 ? "créateur" : "compositeur";

    for (Compositeur composer : oeuvre.compositeur) {
      String activityUri = this.uri + "/activity/" + ++ipCount;
      E21_Person artist = new E21_Person(composer);
      this.addProperty(CIDOC.P9_consists_of,
        model.createResource(activityUri)
          .addProperty(RDF.type, CIDOC.E7_Activity)
          .addProperty(CIDOC.P14_carried_out_by, artist.asResource())
          .addProperty(MUS.U31_had_function, function, "fr")
      );
      this.model.add(artist.model);

    }
  }

  public F28_ExpressionCreation(Evenement ev) {
    // Creation of a Performance plan
    super(ev.getId());
    this.setClass(FRBROO.F28_Expression_Creation);
  }
}
