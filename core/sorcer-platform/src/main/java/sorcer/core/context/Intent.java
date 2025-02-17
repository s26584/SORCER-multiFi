/*
 * Copyright 2022 the original author or authors.
 * Copyright 2022 SorcerSoft.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sorcer.core.context;

import com.oracle.jrockit.jfr.EventInfo;
import sorcer.core.signature.LocalSignature;
import sorcer.service.*;
import sorcer.service.modeling.Functionality;

public class Intent extends ServiceContext<Object> {

    private Fi developerFi;

    private Discipline discipline;

    private ServiceFidelity disciplineFi;

    private Signature disciplineSignature;

    private Signature disciplineIntentSignature;

    private Intent disciplineIntent;

    private Intent developmentIntent;

    public Intent() {
        super();
        type = Functionality.Type.DESIGN;
    }

    public Intent(String name) {
        super(name);
        type = Functionality.Type.DESIGN;
    }

    public Intent(String name, String subject, Object value) {
        super(name, subject, value);
        type = Functionality.Type.DESIGN;
    }

    @Override
    public Fi getDeveloperFi() {
        return developerFi;
    }

    public void setDeveloperFi(Fi developerFi) {
        this.developerFi = developerFi;
    }

    public Context getDisciplineIntent() {
        return disciplineIntent;
    }

    public void setDisciplineIntent(Context disciplineIntent) {
        this.disciplineIntent = ( Intent ) disciplineIntent;
    }

    public Context getDevelopmentIntent() {
        return developmentIntent;
    }

    public void setDevelopmentIntent(Context developmentIntent) {
        this.developmentIntent = ( Intent ) developmentIntent;
    }

    public Discipline getDiscipline() {
        if (disciplineFi != null) {
            Object obj = disciplineFi.getSelect();
            if (obj instanceof Discipline) {
                discipline = ( Discipline ) obj;
            } else if (obj instanceof Signature) {
                try {
                    discipline = ( Discipline ) ((LocalSignature)obj).initInstance();
                } catch (SignatureException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public ServiceFidelity getDisciplineFi() {
        return disciplineFi;
    }

    public void setDisciplineFi(ServiceFidelity disciplineFi) {
        this.disciplineFi = disciplineFi;
    }

    public Signature getDisciplineSignature() {
        return disciplineSignature;
    }

    public void setDisciplineSignature(Signature disciplineSignature) {
        this.disciplineSignature = disciplineSignature;
    }

    public Signature getDisciplineIntentSignature() {
        return disciplineIntentSignature;
    }

    public void setDisciplineIntentSignature(Signature disciplineIntentSignature) {
        this.disciplineIntentSignature = disciplineIntentSignature;
    }




}
