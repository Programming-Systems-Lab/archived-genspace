/*
 * The geworkbench project
 * 
 * Copyright (c) 2007 Columbia University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.geworkbench.events;

/**
 * Event handlers, such as {@link ProjectNodeAddedEventHandler} should implement
 * this class.
 * 
 * @author keshav
 * @version $Id: EventHandler.java,v 1.1.1.1 2008-11-21 19:23:57 nankin Exp $
 */
public interface EventHandler {

	public void log();

}
