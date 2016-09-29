/*
 * Copyright (c) 2015 Cisco Systems and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.ncadaptor.impl;

import org.w3c.dom.Node;

/*
 * Called by the XMLReader for a parsed sub-DOM tree.
 */
public interface NodeHandler {

	void process(Node node);
}
