/*
 * Copyright 2010 the original author or authors.
 * Copyright 2010 SorcerSoft.org.
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

package sorcer.core.requestor;

import net.jini.core.transaction.Transaction;
import sorcer.service.*;

public interface Requestor extends Service, Client {

	public Mogram getMogram(String... args) throws MogramException;

	public Transaction getTransaction();

	public void preprocess(String... args) throws MogramException, ContextException;

	public void process(String... args) throws MogramException, ContextException;
	
	public void postprocess(String... args) throws MogramException, ContextException;

}
