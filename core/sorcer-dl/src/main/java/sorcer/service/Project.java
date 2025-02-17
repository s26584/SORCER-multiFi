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
package sorcer.service;

import sorcer.service.modeling.Exploration;
import sorcer.service.modeling.Finalization;

import java.rmi.RemoteException;
import java.util.Map;

public interface Project extends Design {

    public Map<String, Design> getChildren();

    public Design getChild(String name);

    public Fidelity<Finalization> getFinalizerFi();

    public Fidelity<Analysis> getAnalyzerFi();

    public Fidelity<Exploration> getExplorerFi();

    public Fidelity<Exploration> getManagerFi();
    
    public Context analyze(Context intent, Arg... args)
        throws ContextException, RemoteException;

    public Context explore(Context intent, Arg... args)
        throws ContextException, RemoteException;

    public Context manage(Context intent, Arg... args)
        throws ContextException, RemoteException;
}
