/*
 * Distributed as part of c3p0 v.0.9.5.1
 *
 * Copyright (C) 2015 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of EITHER:
 *
 *     1) The GNU Lesser General Public License (LGPL), version 2.1, as 
 *        published by the Free Software Foundation
 *
 * OR
 *
 *     2) The Eclipse Public License (EPL), version 1.0
 *
 * You may choose which license to accept if you wish to redistribute
 * or modify this work. You may offer derivatives of this work
 * under the license you have chosen, or you may provide the same
 * choice of license which you have been offered here.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received copies of both LGPL v2.1 and EPL v1.0
 * along with this software; see the files LICENSE-EPL and LICENSE-LGPL.
 * If not, the text of these licenses are currently available at
 *
 * LGPL v2.1: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 *  EPL v1.0: http://www.eclipse.org/org/documents/epl-v10.php 
 * 
 */

package com.mchange.v2.c3p0.debug;

import java.sql.*;
import com.mchange.v2.log.*;
import com.mchange.v2.c3p0.*;
import com.mchange.v2.sql.filter.*;

public class CloseLoggingConnectionWrapper extends FilterConnection
{
    final static MLogger logger = MLog.getLogger( CloseLoggingConnectionWrapper.class );

    final MLevel level;

    public CloseLoggingConnectionWrapper( Connection conn, MLevel level )
    { 
	super( conn ); 
	this.level = level;
    }

    public void close() throws SQLException
    {
	super.close();
	if ( logger.isLoggable( level ) )
	    logger.log( level, "DEBUG: A Connection has closed been close()ed without error.", 	new SQLWarning("DEBUG STACK TRACE -- Connection.close() was called.") );
    }
}
