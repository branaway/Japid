# Here you can create play commands that are specific to the module
# Example below:
# ~~~~
if play_command == 'japid:gen':
        check_application()
        do_classpath()
        do_java('cn.bran.play.JapidCommands')
        print "~ Ctrl+C to stop"
        java_cmd.append('gen')
#	print java_cmd
	subprocess.call(java_cmd, env=os.environ)
        print
        sys.exit(0)

if play_command == 'japid:regen':
        check_application()
        do_classpath()
        do_java('cn.bran.play.JapidCommands')
        print "~ Ctrl+C to stop"
        java_cmd.append('regen')
        subprocess.call(java_cmd, env=os.environ)
        print
        sys.exit(0)

if play_command == 'japid:clean':
        check_application()
        do_classpath()
        do_java('cn.bran.play.JapidCommands')
        print "~ Ctrl+C to stop"
        java_cmd.append('clean')
        subprocess.call(java_cmd, env=os.environ)
        print
        sys.exit(0)
